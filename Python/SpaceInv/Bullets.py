import pygame

def shoot(bullets, player_x, player_y, player_width, bullet_width):
    bullet_x = player_x + player_width // 2 -bullet_width // 2
    bullet_y = player_y
    bullets.append([bullet_x, bullet_y])

def update_bullets(bullets, bullet_speed, bullet_height):
    for bullet in bullets[:]:
        bullet[1] -= bullet_speed
        if bullet[1] < -bullet_height:
            bullets.remove(bullet)

def get_bullet_rect(bullet, bullet_img_width, bullet_img_height, bullet_hitbox_width, bullet_hitbox_height):
    bullet_hitbox_x = bullet[0] + (bullet_img_width - bullet_hitbox_width) // 2
    bullet_hitbox_y = bullet[1] + (bullet_img_height - bullet_hitbox_height) // 2

    return pygame.Rect(
        bullet_hitbox_x,
        bullet_hitbox_y,
        bullet_hitbox_width,
        bullet_hitbox_height
    )

def draw_bullets(screen, bullets, bullet_img,
                 bullet_img_width, bullet_img_height,
                 bullet_hitbox_width, bullet_hitbox_height,
                 draw_hitbox, show_hitboxes, color):

    for bullet in bullets:
        screen.blit(bullet_img, (bullet[0], bullet[1]))

        bullet_rect = get_bullet_rect(
            bullet,
            bullet_img_width, bullet_img_height,
            bullet_hitbox_width, bullet_hitbox_height
        )

        draw_hitbox(screen, bullet_rect, color, show_hitboxes)
