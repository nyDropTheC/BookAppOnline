package BackendProg.BookAppWeb.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

public class OpenAiComms {
    private OpenAiService remoteAiService;

    public OpenAiComms() {
        // System.out.println("Openai key: " + System.getenv("BOOKAPP_OPENAI_KEY"));
        // Fix timeouts
        remoteAiService = new OpenAiService(System.getenv("BOOKAPP_OPENAI_KEY"), Duration.ZERO);
    }

    public OpenAiService getService() {
        return remoteAiService;
    }

    public String getBookRecommendationText(String bookName, String bookAuthors) {
        List<ChatMessage> messages = new ArrayList<>();

        messages.add(new ChatMessage(
            ChatMessageRole.SYSTEM.value(), 
            "You are a bot meant to summarize books in an engaging, entertaining and enticing way."
        ));

        messages.add(new ChatMessage(
            ChatMessageRole.USER.value(),
            String.format("Please summarize the book %s by the author(s) %s for me. Make the summary interesting and entertaining. Separate the summary into paragraphs.", bookName, bookAuthors)
        ));

        // Could probably add something to customize the used model, maybe someone will want to shell out the cash for GPT-4 lol
        ChatCompletionRequest req = ChatCompletionRequest
                                        .builder()
                                        .model("gpt-3.5-turbo")
                                        .messages(messages)
                                        .temperature(1.1)
                                        .build();

        ChatMessage resp = getService().createChatCompletion(req).getChoices().get(0).getMessage();
        
        return resp.getContent();
    }
}
