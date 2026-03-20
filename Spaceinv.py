import pygame
import sys
import logging
# import yaml
import random

pygame.init()

# SPIELFENSTER ERSTELLEN
WIDTH  = 800
HEIGHT = 600
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Space Invaders")


# KONSTANTE

# Spaceshipgröße verändern
PLAYER_WIDTH  = 100
PLAYER_HEIGHT = 200

# Alienshipgröße verändern
ENEMY_WIDTH  = 100
ENEMY_HEIGHT = 100

# Alienhitbox größe
ENEMY_HITBOX_WIDTH  = 60
ENEMY_HITBOX_HEIGHT = 40

# Bullet größe/speed
BULLET_WIDTH  =  6
BULLET_HEIGHT = 20
BULLET_SPEED  =  8

# Anpassung der Spielfigur fürs überschreiten vom Rand
LEFT_MARGIN   = -30
RIGHT_MARGIN  =  30
TOP_MARGIN    =   0
BOTTOM_MARGIN =  60


# BILDER LADEN

# SPACESHIP BÄÄÄÄÄÄÄM
player_img = pygame.image.load("SpaceInv.png").convert_alpha()
player_img = pygame.transform.scale(player_img, (PLAYER_WIDTH, PLAYER_HEIGHT))

# ALIENSHIP BAAAABÄÄÄÄÄÄM
enemy_img = pygame. image.load("SpaceInvAlien.png").convert_alpha()
enemy_img = pygame.transform.smoothscale(enemy_img, (ENEMY_WIDTH, ENEMY_HEIGHT))

# HINTERGRUND
background_img = pygame.image.load("SpaceInvBackground.png").convert()
background_img = pygame.transform.scale(background_img, (WIDTH, HEIGHT))


# KONFIG LADEN

# with open("config.yaml", "r") as config_file:
# Konfiguration auslesen und yaml-inhalt parsen
#    config = yaml.safe_load(config_file)
# WHITE = tuple(config["WHITE"])

WHITE = (255, 255, 255)
RED   = (255,   0,   0)
BLACK = (  0,   0,   0)


# LOGGING

logging.basicConfig(level=logging.DEBUG, format= '%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)
logger.debug("Spiel gestartet")


# SCHRIFT

font = pygame.font.SysFont(None, 36)


# SPIELER

player_x    = 400
player_y    = 300
speed       =   6


# BULLETS

# Liste wird erstellt
bullets = []


# GEGNER

enemies = []
enemy_speed       = 2
enemy_spawn_timer = 0
enemy_spawn_delay = 40 # je kleiner, desto öfter spawnen neue Gegner

score = 0

def spawn_enemy():
    enemy_x = random.randint(0, WIDTH - ENEMY_WIDTH)
    enemy_y = -ENEMY_HEIGHT
    enemies.append([enemy_x, enemy_y])

def update_enemies():
    for enemy in enemies [:]:
        enemy[1] += enemy_speed

        if enemy[1] > HEIGHT:
            enemies.remove(enemy)

def draw_enemies():
    for enemy in enemies:
        screen.blit (enemy_img, (enemy[0], enemy[1]))
        
        enemy_hitbox_x = enemy[0] + (ENEMY_WIDTH - ENEMY_HITBOX_WIDTH) // 2
        enemy_hitbox_y = enemy[1] + (ENEMY_HEIGHT - ENEMY_HITBOX_HEIGHT) // 2

        pygame.draw.rect(
            screen,
            RED,
            (enemy_hitbox_x, enemy_hitbox_y, ENEMY_HITBOX_WIDTH, ENEMY_HITBOX_HEIGHT),
            2
        )

# SPIELER BEWEGUNG

def handle_input():
    global player_x, player_y

    keys = pygame.key.get_pressed()

    dx = keys[pygame.K_RIGHT] - keys[pygame.K_LEFT]
    dy = keys[pygame.K_DOWN] - keys[pygame.K_UP]

    player_x += dx * speed
    player_y += dy * speed

    player_x = max(LEFT_MARGIN, min(player_x, WIDTH - PLAYER_WIDTH + RIGHT_MARGIN))
    player_y = max(TOP_MARGIN, min(player_y, HEIGHT - PLAYER_HEIGHT + BOTTOM_MARGIN))


# BULLETS

def shoot():
    bullet_x = player_x + PLAYER_WIDTH // 2 - BULLET_WIDTH // 2
    bullet_y = player_y
    bullets.append([bullet_x, bullet_y])

def update_bullets():
    for bullet in bullets[:]:
        bullet[1] -= BULLET_SPEED

        if bullet[1] < -BULLET_HEIGHT:
            bullets.remove(bullet)

def draw_bullets():
    for bullet in bullets:
        pygame.draw.rect(screen, RED, (bullet[0], bullet[1], BULLET_WIDTH, BULLET_HEIGHT))


# KOLLISION

def check_collision():
    global score

    for bullet in bullets[:]:
        bullet_rect = pygame.Rect(bullet[0], bullet[1], BULLET_WIDTH, BULLET_HEIGHT)

        for enemy in enemies[:]:
            enemy_hitbox_x = enemy[0] + (ENEMY_WIDTH - ENEMY_HITBOX_WIDTH) // 2
            enemy_hitbox_y = enemy[1] + (ENEMY_HEIGHT - ENEMY_HITBOX_HEIGHT) // 2

            enemy_rect = pygame.Rect(
                    enemy_hitbox_x,
                    enemy_hitbox_y,
                    ENEMY_HITBOX_WIDTH,
                    ENEMY_HITBOX_HEIGHT
            )
            if bullet_rect.colliderect(enemy_rect):
                if bullet in bullets:
                    bullets.remove(bullet)
                if enemy in enemies:
                    enemies.remove(enemy)
                score += 1
                break

def draw_score():
    text = font.render(f"Punkte: {score}", True, WHITE)
    screen.blit(text, (10, 10))

clock = pygame.time.Clock()

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()

        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
                shoot()

    handle_input()
    update_bullets()
    
    # Gegner spawn timer
    enemy_spawn_timer += 1
    if enemy_spawn_timer >= enemy_spawn_delay:
        spawn_enemy()
        enemy_spawn_timer = 0

    update_enemies()
    check_collision()

    screen.blit(background_img, (0, 0))

    # Schüsse zeichnen
    draw_bullets()

    # Gegner zeichen
    draw_enemies()

    # score zeichen
    draw_score()
    
    screen.blit(player_img, (player_x, player_y))

    pygame.display.flip()
    clock.tick(60)
