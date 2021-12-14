<template>
  <div id="app">
    <div id="nav">
      <!-- <img :src="require('public\shopping_logo.png')"/> -->
      <div>
        <router-link v-bind:to="{ name: 'groups' }" id="title-of-project">
          Shared Shopping List
        </router-link>
        ||
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
        >
          Logout
        </router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>

export default {
  data() {
    return {
      route: this.$route.fullPath,
      routeArr: [],
    };
  },
  mounted() {
    this.routeArr = this.route.split("/");
    console.log(this.routeArr);
  },
};
</script>

<style>
html,
body {
  font-family: "Inter", sans-serif;
  color: #555b6d;
}
html,
body {
  height: 100%;
  margin: 0px;
}

div#nav {
  padding: 20px 20px 0px 20px;
}
div#nav a {
  text-decoration: none;
}

div#nav a:hover {
  color: #1f7a8c;
}

#app {
  width: 100%;
  height: 100%;
  background-color: #e1e5f2;
  align-items: center;
  filter: drop-shadow(0px 4px 4px rgba(221, 221, 221, 0.25));
  overflow-y: hidden;
}
div.auth-container {
  width: 100%;
  height: calc(100% - 120px);
  display: flex;
  justify-content: center;
  align-items: center;
}
form {
  /* min-height: 420px; */
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
h1,
h2,
h3 {
  font-weight: 700;
  margin-block-end: 0;
}
h1 {
  font-size: 22px;
}
h2 {
  font-size: 18px;
}
h3 {
  font-size: 16px;
}
hr {
  background-color: #e1e5f25c;
  border: none;
  height: 4px;
  border-radius: 1px;
  width: 100%;
}
label.sr-only {
  display: flex;
  flex-direction: column;
  margin-bottom: 2rem;
  font-weight: 500;
}
label.sr-only span {
  margin-bottom: 4px;
}
input[type="text"],
input[type="password"],
input[type="email"],
input[type="number"],
textarea {
  /* margin-top: 4px; */
  padding: 10px;
  background-color: #e1e5f2;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  font-family: "Inter", sans-serif;
  font-weight: 400 !important;
}
input[type="text"]:focus-visible,
input[type="email"]:focus-visible,
input[type="number"]:focus-visible,
input[type="password"]:focus-visible,
textarea:focus-visible {
  outline-color: hsl(226deg 40% 70%);
}
button {
  padding: 10.5px;
  background-color: #1f7a8c;
  border: none;
  border-radius: 4px;
  margin-top: 1rem;
  font-weight: 600;
  border: 2px solid transparent;
  color: #ffffff;
  transition: all 250ms cubic-bezier(0.23, 1, 0.32, 1);
}
button:hover {
  cursor: pointer;
  background-color: #ffffff;
  color: #1f7a8c;
  border: 2px solid #1f7a8c;
}
::placeholder {
  color: rgba(0, 0, 0, 0.4);
  font-weight: 500;
}
a.helper-link {
  color: #1f7a8c;
  font-size: 0.8rem;
  font-weight: 600;
}

textarea {
  min-width: 350px;
  max-width: 350px;
  box-sizing: border-box;
  background-color: #e1e5f2;
  border: none;
  border-radius: 4px;
  padding: 10px;
  font-family: "Inter", sans-serif;
  font-weight: 500;
}
.vertical-list {
  display: flex;
  flex-direction: column;
  padding-inline-start: 0px;
}
span {
  text-align: left;
}
.group-list-item {
  background-color: #e1e5f273;
  padding: 15px;
  margin: 10px;
  text-decoration: none;
  color: #555b6d;
  border-radius: 6px;
  border: 2px solid transparent;
  transition: all 150ms cubic-bezier(0.23, 1, 0.32, 1);
}
.group-list-item:hover {
  border: 2px solid #111c1e0a;
}
.group-list-item:hover > .top-line h1 {
  color: #1f7a8c;
}
#title-of-project {
  text-decoration: none;
  color: #1f7a8c;
}
.breadcrumbs {
  display: flex;
  flex-direction: row;
}
#nav {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
</style>
