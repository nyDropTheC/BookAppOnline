package BackendProg.BookAppWeb.util;

import java.util.ArrayList;
import java.util.List;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.completion.chat.ChatCompletionRequest.ChatCompletionRequestBuilder;
import com.theokanning.openai.service.OpenAiService;

public class OpenAiComms {
    private OpenAiService remoteAiService;

    public OpenAiComms() {
        System.out.println("Openai key: " + System.getenv("BOOKAPP_OPENAI_KEY"));
        remoteAiService = new OpenAiService(System.getenv("BOOKAPP_OPENAI_KEY"));
    }

    public OpenAiService getService() {
        return remoteAiService;
    }

    public String getBookRecommendationText(String bookName, String bookAuthors) {
        List<ChatMessage> messages = new ArrayList<>();

        messages.add(new ChatMessage(
            ChatMessageRole.USER.value(), 
            "Hello there! Please introduce yourself in detail."
        ));

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
