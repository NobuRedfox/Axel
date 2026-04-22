import pygame
import sys
import logging

from Settings import (
    WIDTH, HEIGHT, COLORS, FPS,
    PLAYER_WIDTH, PLAYER_HEIGHT,
    PLAYER_HITBOX_WIDTH, PLAYER_HITBOX_HEIGHT,
    PLAYER_SPEED,
    ENEMY_WIDTH, ENEMY_HEIGHT,
    ENEMY_HITBOX_WIDTH, ENEMY_HITBOX_HEIGHT,
    ENEMY_SPEED, ENEMY_SPAWN_DELAY,
    BULLET_IMG_WIDTH, BULLET_IMG_HEIGHT,
    BULLET_WIDTH, BULLET_HEIGHT, BULLET_SPEED,
    SHOOT_COOLDOWN,
    LEFT_MARGIN, RIGHT_MARGIN, TOP_MARGIN, BOTTOM_MARGIN,
    BOSS_WIDTH, BOSS_HEIGHT, BOSS_HP, BOSS_HITBOX_WIDTH, BOSS_HITBOX_HEIGHT
)

from Assets import load_assets
from Player import handle_input, update_player_hitbox
from Bullets import shoot, update_bullets, draw_bullets, get_bullet_rect
from Collision import check_collision, check_player_hit
from Enemies import spawn_enemy, spawn_boss, update_enemies, draw_enemies
from UI import draw_score, draw_lives, draw_game_over, draw_hitbox

pygame.init()


# SPIELFENSTER ERSTELLEN
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Space Invaders")


player_img, enemy_img, background_img, bullet_img, explosion_img, boss_img = load_assets(
    WIDTH, HEIGHT,
    (PLAYER_WIDTH, PLAYER_HEIGHT),
    (ENEMY_WIDTH, ENEMY_HEIGHT),
    BOSS_WIDTH, BOSS_HEIGHT
)


# LOGGING

logging.basicConfig(level=logging.DEBUG, format= '%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)
logger.debug("Spiel gestartet")


# SCHRIFT
font = pygame.font.SysFont(None, 36)

# Explosion
explosions = []

# SPIELER
player_x     = 400
player_y     = 300

# BULLETS

# Liste wird erstellt
bullets = []
last_shot_time = 0

# GEGNER
enemies = []
enemy_spawn_timer = 0
spawned_enemy_count = 0

score = 0
lives = 3
game_over = False


# BOSS
boss = None
boss_bullets = []
boss_active = False
boss_defeated = False

# HITBOX anzeigen

show_hitboxes = False

# ENDSCREEN UND RESTART
# Game over

def reset_game():
    global player_x, player_y, bullets, enemies, explosions
    global enemy_spawn_timer, score, lives, game_over, spawned_enemy_count
    global boss, boss_active, boss_defeated, boss_bullets

    player_x = 400
    player_y = 300
    bullets = []
    enemies = []
    explosions = []
    enemy_spawn_timer = 0
    score = 0
    lives = 3
    game_over = False
    spawned_enemy_count = 0

    boss = None
    boss_active = False
    boss_defeated = False
    boss_bullets = []

def update_explosions(explosions):
    for exp in explosions[:]:
        exp["timer"] -= 1
        if exp["timer"] <= 0:
            explosions.remove(exp)

def draw_explosions(screen, explosions, explosion_img):
    for exp in explosions:
        x = exp["x"] - explosion_img.get_width() // 2
        y = exp["y"] - explosion_img.get_height() // 2 - 10  # kleiner Offset
        screen.blit(explosion_img, (x, y))

def update_boss_bullets(boss_bullets, height, width):
    for bullet in boss_bullets[:]:
        bullet[0] += bullet[2] # vx
        bullet[1] += bullet[3] # vy

        if bullet[1] > height or bullet [0] < 0 or bullet[0] > width:
            boss_bullets.remove(bullet)

def draw_boss_bullets(screen, boss_bullets):
    for bullet in boss_bullets:
        pygame.draw.circle(screen, (255, 120, 0), (bullet[0], bullet[1]), 8)

def check_boss_hit(bullets, boss, explosions):
    boss_rect = boss.get_rect(BOSS_HITBOX_WIDTH, BOSS_HITBOX_HEIGHT)

    for bullet in bullets[:]:
        bullet_rect = get_bullet_rect(
            bullet,
            BULLET_IMG_WIDTH, BULLET_IMG_HEIGHT,
            BULLET_WIDTH, BULLET_HEIGHT
        )

        if bullet_rect.colliderect(boss_rect):
            bullets.remove(bullet)
            boss.hp -= 1

            explosions.append({
                "x": boss.x + boss.width // 2,
                "y": boss.y + boss.height // 2,
                "timer": 8
            })

            if boss.hp <= 0:
                return True

    return False

def check_boss_bullet_hit(boss_bullets, player_rect):
    hits = 0

    for bullet in boss_bullets[:]:
        bullet_rect = pygame.Rect(bullet[0] - 6, bullet[1] - 6, 12, 12)

        if bullet_rect.colliderect(player_rect):
            boss_bullets.remove(bullet)
            hits += 1

    return hits


clock = pygame.time.Clock()
running = True

while running:
    screen.blit(background_img, (0, 0))
    draw_lives(screen, font, lives, COLORS["white"])

    current_time = pygame.time.get_ticks()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_ESCAPE:
                pygame.quit()
                sys.exit()

            # Hitbox ein- ausblenden
            if event.key == pygame.K_h:
                show_hitboxes = not show_hitboxes

            # OPTIONAL: Nur noch für Restart
            if game_over and event.key == pygame.K_r:
                reset_game()

    # Bewegung nur wenn Spiel läuft
    if not game_over:
        player_x, player_y = handle_input(
                player_x, player_y, PLAYER_SPEED,
                WIDTH, HEIGHT,
                PLAYER_WIDTH, PLAYER_HEIGHT,
                LEFT_MARGIN, RIGHT_MARGIN, TOP_MARGIN, BOTTOM_MARGIN
        )

        if pygame.key.get_pressed()[pygame.K_SPACE]:
            if current_time - last_shot_time > SHOOT_COOLDOWN:
                shoot(bullets, player_x, player_y , PLAYER_WIDTH, BULLET_IMG_WIDTH)
                last_shot_time = current_time

        if score >= 100 and not boss_active and not boss_defeated:
            boss = spawn_boss(WIDTH, BOSS_WIDTH, BOSS_HEIGHT, hp=BOSS_HP)
            boss_active = True
            enemies.clear()


        update_bullets(bullets, BULLET_SPEED, BULLET_IMG_HEIGHT)

        if not boss_active:
            update_enemies(enemies, WIDTH)

            enemy_spawn_timer += 1
            if enemy_spawn_timer >= ENEMY_SPAWN_DELAY:
                spawn_enemy(enemies, WIDTH, ENEMY_WIDTH, ENEMY_HEIGHT, spawned_enemy_count)
                spawned_enemy_count += 1
                enemy_spawn_timer = 0
        else:
            # boss aktiv
            if boss: # <-- wichtig!
                boss.update(WIDTH)
            
                # boss schießt
                boss.shoot_timer += 1
                if boss.shoot_timer >= 30:
                    boss_bullets.extend(boss.shoot())
                    boss.shoot_timer = 0

            update_boss_bullets(boss_bullets, HEIGHT, WIDTH)


        score += check_collision(
                bullets, enemies,
                BULLET_IMG_WIDTH, BULLET_IMG_HEIGHT,
                BULLET_WIDTH, BULLET_HEIGHT,
                ENEMY_HITBOX_WIDTH, ENEMY_HITBOX_HEIGHT,
                explosions
        )

        if boss_active and boss:
            boss_dead = check_boss_hit(bullets, boss, explosions)

            if boss_dead:
                explosions.append({
                    "x": boss.x + boss.width // 2,
                    "y": boss.y + boss.height // 2,
                    "timer": 20
                })
                boss = None
                boss_active = False
                boss_defeated = True
                boss_bullets.clear()

        update_explosions(explosions)

        player_rect = update_player_hitbox(
            player_x, player_y,
            PLAYER_WIDTH, PLAYER_HEIGHT,
            PLAYER_HITBOX_WIDTH, PLAYER_HITBOX_HEIGHT
        )

        lives_lost = check_player_hit(
            enemies, player_rect, HEIGHT,
            ENEMY_HITBOX_WIDTH, ENEMY_HITBOX_HEIGHT
        )

        lives -= lives_lost

        if boss_active and boss:
            lives -= check_boss_bullet_hit(boss_bullets, player_rect)

        if lives <= 0:
            lives = 0
            game_over = True

    screen.blit(player_img, (player_x, player_y))

    player_rect = update_player_hitbox(
        player_x, player_y,
        PLAYER_WIDTH, PLAYER_HEIGHT,
        PLAYER_HITBOX_WIDTH, PLAYER_HITBOX_HEIGHT
    )
    draw_hitbox(screen, player_rect, COLORS["green"], show_hitboxes)

    draw_bullets(
        screen, bullets, bullet_img,
        BULLET_IMG_WIDTH, BULLET_IMG_HEIGHT,
        BULLET_WIDTH, BULLET_HEIGHT,
        draw_hitbox, show_hitboxes,
        COLORS["green"]
    )

    draw_enemies(
        screen, enemies, enemy_img,
        ENEMY_HITBOX_WIDTH, ENEMY_HITBOX_HEIGHT,
        draw_hitbox, COLORS, show_hitboxes
    )

    if boss_active and boss:
        boss.draw(screen, boss_img)
        boss.draw_hp_bar(screen)

        if show_hitboxes:
            boss_rect = boss.get_rect(BOSS_HITBOX_WIDTH, BOSS_HITBOX_HEIGHT)
            draw_hitbox(screen, boss_rect, COLORS["red"], show_hitboxes)

        draw_boss_bullets(screen, boss_bullets)

    draw_explosions(screen, explosions, explosion_img)
    draw_score(screen, font, score, COLORS["white"])

    if game_over:
        draw_game_over(screen, WIDTH, HEIGHT, score, COLORS["red"], COLORS["white"])

    pygame.display.update()
    clock.tick(FPS)
