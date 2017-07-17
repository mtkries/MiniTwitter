package testing;

import users.SingleUser;
import users.Users;

public class TestMain {

	public static void main(String[] args) {
		SingleUser temp1 = new SingleUser("1");
		SingleUser temp2 = new SingleUser("2");
		
		temp1.addFollowing(temp2);
		temp1.addFollowing(temp2);
		temp1.addFollowing(temp2);
		
		
	}

}
