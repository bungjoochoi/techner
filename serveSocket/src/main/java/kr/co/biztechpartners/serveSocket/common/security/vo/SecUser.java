package kr.co.biztechpartners.serveSocket.common.security.vo;

import javax.validation.constraints.NotBlank;

public class SecUser {
	
	private int SEQ;

    private long id;

    private long version;

    private boolean account_expired;

    private boolean account_locked;

    private boolean enabled;

    private String password;

    private boolean password_expired;

    private String username;
    
    private String last_updated;
    
    private int password_incorrectcount;

    private String last_login_date;
    
    
    public int getSEQ() {
		return SEQ;
	}

	public void setSEQ(int sEQ) {
		SEQ = sEQ;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean isAccount_expired() {
        return account_expired;
    }

    public void setAccount_expired(boolean account_expired) {
        this.account_expired = account_expired;
    }

    public boolean isAccount_locked() {
        return account_locked;
    }

    public void setAccount_locked(boolean account_locked) {
        this.account_locked = account_locked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPassword_expired() {
        return password_expired;
    }

    public void setPassword_expired(boolean password_expired) {
        this.password_expired = password_expired;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
	
	public int getPassword_incorrectcount() {
		return password_incorrectcount;
	}

	public void setPassword_incorrectcount(int password_incorrectcount) {
		this.password_incorrectcount = password_incorrectcount;
	}
	
	public String getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}
    
}
