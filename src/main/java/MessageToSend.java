import java.util.Arrays;

public class MessageToSend {
    private static String[] array;
    private static String testName,slackWebhookUrl;
    private static Integer testPass,testFail;
    public void slack(String[] array, Integer testPass, Integer testFail,String testName) {
        //QA  = "https://hooks.slack.com/services/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";
        String details;

        if(testFail != 0){

            details = Arrays.toString(array);
            slackWebhookUrl = "https://hooks.slack.com/services/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";
            SlackMessage slackMessage = SlackMessage.builder()
                    .channel("automation-fialures")
                    .username("test")
                    .text("Test Case Name: " + testName + "\nTests that failed: " + testFail + "\nDetails: "+array[0])
                    .icon_emoji(":twice:")
                    .build();
            SlackUtils.sendMessage(slackMessage,slackWebhookUrl);
        }
        if(testPass != 0 && testFail == 0) {
            slackWebhookUrl = "https://hooks.slack.com/services/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";
            SlackMessage slackMessage = SlackMessage.builder()
                    .username("test")
                    .text("Test Case Name: " + testName + "\n" + "" + "Tests that passed: "+ testPass)
                    .icon_emoji(":twice:")
                    .build();
            SlackUtils.sendMessage(slackMessage, slackWebhookUrl);
        }
    }
}
