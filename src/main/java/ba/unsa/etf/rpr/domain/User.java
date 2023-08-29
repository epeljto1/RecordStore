package ba.unsa.etf.rpr.domain;

/**
 * Bean class for users
 * @author Emina Peljto
 */
public class User implements Identifiable {
    private int id;
    private String username;
    private String password;

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
