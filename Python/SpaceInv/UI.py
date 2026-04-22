import pygame


def draw_score(screen, font, score, color):
    text = font.render(f"Punkte: {score}", True, color)
    screen.blit(text, (10, 10))


def draw_lives(screen, font, lives, color):
    text = font.render(f"Leben: {lives}", True, color)
    screen.blit(text, (10, 50))


def draw_game_over(screen, width, height, score, red, white):
    big_font = pygame.font.SysFont(None, 72)
    small_font = pygame.font.SysFont(None, 36)

    game_over_text = big_font.render("GAME OVER", True, red)
    score_text = small_font.render(f"Endstand: {score}", True, white)
    restart_text = small_font.render("Druecke R fuer Neustart", True, white)
    quit_text = small_font.render("Druecke ESC zum Beenden", True, white)

    screen.blit(game_over_text, (
        width // 2 - game_over_text.get_width() // 2,
        height // 2 - 100
    ))
    screen.blit(score_text, (
        width // 2 - score_text.get_width() // 2,
        height // 2 - 20
    ))
    screen.blit(restart_text, (
        width // 2 - restart_text.get_width() // 2,
        height // 2 + 30
    ))
    screen.blit(quit_text, (
        width // 2 - quit_text.get_width() // 2,
        height // 2 + 70
    ))


def draw_hitbox(screen, rect, color, show_hitboxes):
    if show_hitboxes:
        pygame.draw.rect(screen, color, rect, 2)
