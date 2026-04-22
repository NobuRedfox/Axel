import pygame


def get_enemy_rect(enemy, enemy_hitbox_width, enemy_hitbox_height):
    return enemy.get_rect(enemy_hitbox_width, enemy_hitbox_height)


def get_bullet_rect(bullet, bullet_img_width, bullet_img_height, bullet_hitbox_width, bullet_hitbox_height):
    bullet_hitbox_x = bullet[0] + (bullet_img_width - bullet_hitbox_width) // 2
    bullet_hitbox_y = bullet[1] + (bullet_img_height - bullet_hitbox_height) // 2

    return pygame.Rect(
        bullet_hitbox_x,
        bullet_hitbox_y,
        bullet_hitbox_width,
        bullet_hitbox_height
    )


def check_collision(bullets, enemies,
                    bullet_img_width, bullet_img_height,
                    bullet_hitbox_width, bullet_hitbox_height,
                    enemy_hitbox_width, enemy_hitbox_height,
                    explosions):
    score_increase = 0

    for bullet in bullets[:]:
        bullet_rect = get_bullet_rect(
            bullet,
            bullet_img_width, bullet_img_height,
            bullet_hitbox_width, bullet_hitbox_height
        )

        for enemy in enemies[:]:
            enemy_rect = get_enemy_rect(
                enemy,
                enemy_hitbox_width, enemy_hitbox_height
            )

            if bullet_rect.colliderect(enemy_rect):
                explosions.append({
                    "x": enemy.x + enemy.width // 2,
                    "y": enemy.y + enemy.height // 2,
                    "timer": 10
                })

                bullets.remove(bullet)
                enemies.remove(enemy)
                score_increase += 1
                break

    return score_increase


def check_player_hit(enemies, player_rect, height,
                     enemy_hitbox_width, enemy_hitbox_height):
    lives_lost = 0

    for enemy in enemies[:]:
        enemy_rect = get_enemy_rect(
            enemy,
            enemy_hitbox_width, enemy_hitbox_height
        )

        if enemy_rect.colliderect(player_rect):
            enemies.remove(enemy)
            lives_lost += 1

        elif enemy.y > height:
            enemies.remove(enemy)
            lives_lost += 1

    return lives_lost
