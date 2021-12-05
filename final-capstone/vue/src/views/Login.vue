<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="sr-only">Username
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      </label>
      <label for="password" class="sr-only">Password
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </label>
    
      <button type="submit">Sign in</button>

      <router-link :to="{ name: 'register' }">Need an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style>

html,body {
  height: 100%;
  margin: 0px;
}

#app {
  width: 100%;
  height: 100%;
  background-color: #E1E5F2;
  align-items: center;
  filter: drop-shadow(0px 4px 4px rgba(221, 221, 221, 0.25));
}
div#login {
  width: 100%;
  height: calc(100% - 18px);
  display: flex;
  justify-content: center;
  align-items: center;
}
form.form-signin {
  min-height: 420px;
  height: fit-content;
  min-width: 350px;
  width: fit-content;
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  border-radius: 6px;
  filter: drop-shadow(0px 4px 4px rgba(221, 221, 221, 0.25));
  padding: 25px;
}
label.sr-only {
    display: flex;
    flex-direction: column;
    margin-bottom: 6px;
}
input[type=text], input[type=password] {
  margin-top: 4px;
  padding: 10px;
  background-color: #E1E5F2;
  border: none;
  border-radius: 4px;
}
::placeholder {
  color: rgb(0, 0, 0);
  opacity: 60%;
}
</style>