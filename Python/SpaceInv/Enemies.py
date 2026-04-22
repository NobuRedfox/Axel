import random
import pygame


class Enemy:
    def __init__(self, x, y, width, height):
        self.x = x
        self.y = y
        self.width = width
        self.height = height

    def update(self, screen_width):
        pass

    def draw(self, screen, enemy_img):
        screen.blit(enemy_img, (self.x, self.y))

    def get_rect(self, hitbox_width, hitbox_height):
        hitbox_x = self.x + (self.width - hitbox_width) // 2
        hitbox_y = self.y + (self.height - hitbox_height) // 2

        return pygame.Rect(
            hitbox_x,
            hitbox_y,
            hitbox_width,
            hitbox_height
        )


class NormalEnemy(Enemy):
    def __init__(self, x, y, width, height, speed=2):
        super().__init__(x, y, width, height)
        self.speed = speed
        self.enemy_type = "normal"

    def update(self, screen_width):
        self.y += self.speed


class ZigZagEnemy(Enemy):
    def __init__(self, x, y, width, height, speed_x=3, speed_y=1.5):
        super().__init__(x, y, width, height)
        self.speed_x = speed_x
        self.speed_y = speed_y
        self.direction = random.choice([-1, 1])
        self.enemy_type = "zigzag"

    def update(self, screen_width):
        self.x += self.direction * self.speed_x
        self.y += self.speed_y

        if self.x <= 0 or self.x >= screen_width - self.width:
            self.direction *= -1


class Boss(Enemy):
    def __init__(self, x, y, width, height, hp=30, speed=3):
        super().__init__(x, y, width, height)
        self.hp = hp
        self.max_hp = hp
        self.speed = speed
        self.direction = 1
        self.enemy_type = "boss"
        self.shoot_timer = 0

    def update(self, screen_width):
        self.x += self.direction * self.speed

        if self.x <= 0 or self.x >= screen_width - self.width:
            self.direction *= -1

    def shoot(self):
        cx = self.x + self.width // 2
        cy = self.y + 110

        return [
            [cx, cy, -2, 5], # links schräg
            [cx, cy,  0, 5], # gerade runter
            [cx, cy,  2, 5]  # rechts schräg
        ]

    def draw_hp_bar(self, screen):
        bar_width = 200
        bar_height = 14
        x = screen.get_width() // 2 - bar_width // 2
        y = 10

        current_width = int((self.hp / self.max_hp) * bar_width)

        pygame.draw.rect(screen, (80, 80, 80), (x, y, bar_width, bar_height))
        pygame.draw.rect(screen, (255, 0, 0), (x, y, current_width, bar_height))

    def get_rect(self, hitbox_width, hitbox_height):
        hitbox_x = self.x + (self.width - hitbox_width) // 2
        
        #  DAS ist der wichtige Teil (nach oben verschieben)
        hitbox_y = self.y + 65

        return pygame.Rect(
            hitbox_x,
            hitbox_y,
            hitbox_width,
            hitbox_height
        )


def spawn_enemy(enemies, width, enemy_width, enemy_height, spawned_enemy_count):
    enemy_x = random.randint(0, width - enemy_width)
    enemy_y = -100

    if spawned_enemy_count <= 50:
        enemy = NormalEnemy(enemy_x, enemy_y, enemy_width, enemy_height)
    else:
        enemy_class = random.choice([NormalEnemy, ZigZagEnemy])
        enemy = enemy_class(enemy_x, enemy_y, enemy_width, enemy_height)

    enemies.append(enemy)


def spawn_boss(width, boss_width, boss_height, hp=30):
    return Boss(width // 2 - boss_width // 2, 0, boss_width, boss_height, hp=hp)


def update_enemies(enemies, screen_width):
    for enemy in enemies:
        enemy.update(screen_width)


def draw_enemies(screen, enemies, enemy_img,
                 enemy_hitbox_width, enemy_hitbox_height,
                 draw_hitbox, colors, show_hitboxes):

    for enemy in enemies:
        enemy.draw(screen, enemy_img)

        enemy_rect = enemy.get_rect(enemy_hitbox_width, enemy_hitbox_height)

        if enemy.enemy_type == "normal":
            draw_hitbox(screen, enemy_rect, colors["red"], show_hitboxes)
        elif enemy.enemy_type == "zigzag":
            draw_hitbox(screen, enemy_rect, colors["white"], show_hitboxes)
