package br.uff.ic.devweb;

public class CommentBean {
    private String text;

    // user data
    private String avatar;
    private String name;
    private String nickname;

    /* get */
    public String getText() {
        return this.text;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getName() {
        return this.name;
    }

    public String getNickname() {
        return this.nickname;
    }

    /* set */
    public void setText(String text) {
        this.text = text;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
