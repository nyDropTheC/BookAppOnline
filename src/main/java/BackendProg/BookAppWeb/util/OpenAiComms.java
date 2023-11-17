package BackendProg.BookAppWeb.util;

import com.theokanning.openai.service.OpenAiService;

public class OpenAiComms {
    private OpenAiService remoteAiService;

    public OpenAiComms() {
        remoteAiService = new OpenAiService(System.getenv("BOOKAPP_OPENAI_KEY"));
    }

    public OpenAiService getService() {
        return remoteAiService;
    }
}
