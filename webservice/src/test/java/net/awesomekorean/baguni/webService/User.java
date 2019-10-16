package net.awesomekorean.baguni.webService;

public class User {

        private String name;
        private String email;
        private String password;
        private String dateSignUp;
        private String dateSignIn;

        public User(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.dateSignUp = "NOW()";
            this.dateSignIn = "NOW()";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDateSignUp() {
            return dateSignUp;
        }

        public String getDateSignIn() {
            return dateSignIn;
        }

}
