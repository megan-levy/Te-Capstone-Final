<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
  html, body {
    font-family: 'Inter', sans-serif;
    color: #555b6d;
  }
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
  div.auth-container {
    width: 100%;
    height: calc(100% - 18px);
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
  h1, h2, h3 {
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
  input[type=text], input[type=password], input[type=email] {
    /* margin-top: 4px; */
    padding: 10px;
    background-color: #E1E5F2;
    border: none;
    border-radius: 4px;
  }
  input[type=text]:focus-visible, input[type=password]:focus-visible, textarea:focus-visible {
    outline-color: hsl(226deg 40% 70%);
  }
  button {
    padding: 8px;
    background-color: #1F7A8C;
    border: none;
    border-radius: 4px;
    margin-top: 1rem;
    font-weight: 600;
    border: 2px solid transparent;
    color: #ffffff;
    transition: all 250ms cubic-bezier(0.23, 1, 0.320, 1);
  }
  button:hover {
    cursor: pointer;
    background-color: #ffffff;
    color:  #1F7A8C;
    border: 2px solid  #1F7A8C;
  }
  ::placeholder {
    color: rgba(0, 0, 0, 0.4);
    font-weight: 600;
  }
  a.helper-link {
    color: #1F7A8C;
    font-size: .8rem;
    font-weight: 600;
  }

  textarea {
    min-width: 350px;
    max-width: 350px;
    box-sizing: border-box;
    background-color: #E1E5F2;
    border: none;
    border-radius: 4px;
    padding: 10px;
    font-family: 'Inter', sans-serif;
    font-weight: 500;
  }

</style>
