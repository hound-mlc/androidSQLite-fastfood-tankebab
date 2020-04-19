package mlc.tankebab.src;

import java.util.Objects;

public class Usuario {

    private String usuario, pass, kebab_fav;

    public Usuario(){}

    public Usuario(String usuario, String pass){
        this.usuario=usuario;
        this.pass=pass;
        this.kebab_fav="";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getKebab_fav(){return kebab_fav;}

    public void setKebab_fav(String kebab_fav){this.kebab_fav=kebab_fav;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(usuario, usuario1.usuario) &&
                Objects.equals(pass, usuario1.pass);
    }

}
