package kr.co.tjeit.algorithmtest.data;

/**
 * Created by tjoeun on 2017-08-11.
 */

public class ChattingData {

    // 내가 보냈는지, 컴퓨터가 보냈는지
    private boolean isSentByMe;
    // 메세지의 내용을 저장.
    private String messageText;

    public ChattingData() {
    }

    public ChattingData(boolean isSentByMe, String messageText) {
        this.isSentByMe = isSentByMe;
        this.messageText = messageText;
    }

    public boolean isSentByMe() {
        return isSentByMe;
    }

    public void setSentByMe(boolean sentByMe) {
        isSentByMe = sentByMe;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
