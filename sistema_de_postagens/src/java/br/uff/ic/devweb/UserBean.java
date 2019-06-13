package br.uff.ic.devweb;

public class UserBean {
    
    // user data
    private String nickname;

    
    private String senha;
    private String avatar;
    private String name;

    /* get */
    public String getNickname() {
        return this.nickname;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getName() {
        return this.name;
    }
    
    public String getSenha(){
        return this.senha;
    }

    /* set */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
