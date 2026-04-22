import pygame

def handle_input(
    player_x, player_y, player_speed,
    width, height,
    player_width, player_height,
    left_margin, right_margin, top_margin, bottom_margin
):
    keys = pygame.key.get_pressed()

    dx = keys[pygame.K_RIGHT] - keys[pygame.K_LEFT]
    dy = keys[pygame.K_DOWN] - keys[pygame.K_UP]

    player_x += dx * player_speed
    player_y += dy * player_speed

    player_x = max(left_margin, min(player_x, width - player_width + right_margin))
    player_y = max(top_margin, min(player_y, height - player_height + bottom_margin))

    return player_x, player_y


def update_player_hitbox(
        player_x, player_y,
        player_width, player_height,
        player_hitbox_width, player_hitbox_height
):
    player_hitbox_x = player_x + (player_width - player_hitbox_width) // 2
    player_hitbox_y = player_y + (player_height - player_hitbox_height) // 2

    return pygame.Rect(
            player_hitbox_x,
            player_hitbox_y,
            player_hitbox_width,
            player_hitbox_height
    )
