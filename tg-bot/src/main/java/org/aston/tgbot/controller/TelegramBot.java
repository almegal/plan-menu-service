package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.aston.config.BotConfig;
import ru.aston.dto.MealPlanEntryDto;
import ru.aston.repository.AppUserRepo;
import ru.aston.entity.AppUser;

import java.util.List;

@Component
@Log4j
@RequiredArgsConstructor
@RestController
public class TelegramBot extends TelegramLongPollingBot {

    private static final String HELP_TEXT = "Only help text now";
    private final BotConfig config;
    private final AppUserRepo appUserRepo;

    @Override
    public String getBotUsername() {

        return config.getBotName();
    }

    @Override
    public String getBotToken() {

        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            log.debug(update.getMessage().hasText());
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (message) {
                case "/start" -> startCommandReceived(update);
                case "/category" -> startPlanMenu();
                case "/plan" -> showMenu(update.getMessage().getChatId());
                case "/help" -> sendMessage(chatId, HELP_TEXT);
                default -> sendMessage(chatId, "Sorry, command not found\n " +
                    "Please, use command from menu");
            }
        }
    }

    private void startPlanMenu() {
        // TODO
    }

    @GetMapping("/menu-plan/{userId}")
    private void showMenu(Long userId) {

    }

    private void registerUser(Update update) {

        var user = update.getMessage().getFrom();
        var appUserOpt = appUserRepo.findAppUserByChatId(user.getId());
        if (appUserOpt == null) {
            var transientAppUser = AppUser.builder()
                .chatId(user.getId())
                .username(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
            appUserRepo.save(transientAppUser);
            sendMessage(transientAppUser.getChatId(),
                "Hello! Congratulations! You are now registered as a new user!");
            return;
        }
        sendMessage(user.getId(),
            "You are already registered!");
    }

    private void startCommandReceived(Update update) {

        registerUser(update);
        sendMessage(update.getMessage().getChatId(),
            "Now you can start plan your menu for week");
    }

    private void sendMessage(long chatId, String textToSend) {

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
