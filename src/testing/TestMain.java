package testing;

import users.SingleUser;
import users.Users;

public class TestMain {

	public static void main(String[] args) {
		SingleUser temp1 = new SingleUser("1");
		SingleUser temp2 = new SingleUser("2");
		SingleUser temp3 = new SingleUser("3");
		SingleUser temp4 = new SingleUser("4");
		SingleUser temp5 = new SingleUser("5");
		SingleUser temp6 = new SingleUser("6");
		SingleUser temp7 = new SingleUser("7");
		
		temp1.addFollowing(temp2);
		temp1.addFollowing(temp2);
		temp1.addFollowing(temp2);
		
		
	}

}
