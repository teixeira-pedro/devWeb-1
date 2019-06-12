package br.uff.ic.devweb;

public class PostBean {
    private String title;
    private String text;
    
    // user data
    private String avatar;
    private String name;
    private String nickname;
    
    
    //
    private Boolean canDelete;
    
    /* get */
    public String getTitle() {
        return this.title;
    }
    
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
    
    public Boolean getCanDelete() {
        return this.canDelete;
    }
    
    /* set */
    public void setTitle(String title) {
        this.title = title;
    }
    
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
    
    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }
    
}
