package JSPWEB.CH09.SESSION;

public class SESSIONMVO {
	
		private String id;
		private String password;
		
		
		public SESSIONMVO(){}

		public SESSIONMVO(String id, String password){
			this.id = id;
			this.password = password;
			
		}
			public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		
		
		
		


}
