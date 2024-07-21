package TIcTacToe.models;

public class Symbol {
    private char aChar;
    private String avatarURL;

    public Symbol(char aChar){
        this.aChar = aChar;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }
}
