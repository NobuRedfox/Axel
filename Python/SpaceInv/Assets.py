# Assets.py
import pygame
from Settings import BULLET_IMG_WIDTH, BULLET_IMG_HEIGHT

def load_assets(width, height, player_size, enemy_size, boss_width, boss_height):
    player_img = pygame.image.load("SpaceInv.png").convert_alpha()
    player_img = pygame.transform.scale(player_img, player_size)

    enemy_img = pygame.image.load("SpaceInvAlien.png").convert_alpha()
    enemy_img = pygame.transform.smoothscale(enemy_img, enemy_size)

    boss_img = pygame.image.load("Boss.png").convert_alpha()
    boss_img = pygame.transform.smoothscale(boss_img, (boss_width, boss_height))

    background_img = pygame.image.load("SpaceInvBackground.png").convert()
    background_img = pygame.transform.scale(background_img, (width, height))

    bullet_img = pygame.image.load("Bullet.png").convert_alpha()
    bullet_img = pygame.transform.scale(
        bullet_img, (BULLET_IMG_WIDTH, BULLET_IMG_HEIGHT)
    )

    explosion_img = pygame.image.load("Explosion.png").convert_alpha()
    explosion_img = pygame.transform.smoothscale(explosion_img, (64, 64))

    return player_img, enemy_img, background_img, bullet_img, explosion_img, boss_img
