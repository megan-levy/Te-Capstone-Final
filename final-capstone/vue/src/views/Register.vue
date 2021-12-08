<template>
  <div id="register" class="text-center auth-container">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Sign Up</h1>
      <hr>
      <br>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">
        <span>Username (Email)</span>
        <input
          type="email"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
      </label>
      <label for="password" class="sr-only">
        <span>Password</span>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </label>
      <label for="confirmPassword" class="sr-only">
        <span>Confirm Password</span>
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </label>
      
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Sign Up
      </button>
      <br>
      <router-link tag="a" class="helper-link" :to="{ name: 'login' }">Already have an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      let password = this.user.password;
      let passwordChar = password.split("");
      let passLen = passwordChar.length;
      let hasUpper = false;
      let hasLower = false;
      let hasNum = false;

      if (passLen >= 8) {
        for (let i = 0; i < passLen; i++) {
          
          if (passwordChar[i] === passwordChar[i].toUpperCase())  hasUpper = true;
          if (passwordChar[i] === passwordChar[i].toLowerCase()) hasLower = true;
          if (typeof parseInt(passwordChar[i]) === "number") hasNum = true;
        }

        if (hasUpper && hasLower && hasNum) {
          console.log("Conditions Met.");
          if (this.user.password != this.user.confirmPassword) {
            this.registrationErrors = true;
            this.registrationErrorMsg = 'Password & Confirm Password do not match.';
          } else {
            authService
            .register(this.user)
            .then((response) => {
              if (response.status == 201) {
                this.$router.push({
                  path: '/login',
                  query: { registration: 'success' },
                });
              }
            })
            .catch((error) => {
              const response = error.response;
              this.registrationErrors = true;
              if (response.status === 400) {
                this.registrationErrorMsg = 'Bad Request: Validation Errors';
              }
            });
          } 
        } else {
          console.log("Conditions not met.")
        }
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  }
};
</script>

<style></style>
