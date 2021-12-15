<template>
  <div id="new-group" class="text-center auth-container">
     
        <form class="form-new-group" @submit.prevent="joinGroup">
          <h1 class="h3 mb-3 font-weight-normal">Join Group</h1>
          <hr>
          <br>
          <label for="text" class="sr-only">
              <span>Group Code</span>
              <input
              type="text"
              id="group-name"
              class="form-control"
              v-model="code"
              placeholder="Enter 6 Digit Access Code..."
              required/>
          </label>

        <div class="toggleContainer">
          <button class="join" type="submit">
            Join
          </button>
          
        </div>
        <br>
        <hr />
        <br>
        <a class="helper-link" v-on:click="switched(false)">Cancel</a>
        </form>
  </div>
</template>
   


<script>
import MemberService from '../../services/MemberService.js';

export default {
  name: "join-group",
  props:['toggleForm'],
   data() {
       return{
           code: ""
       };
   },  
   methods: {
      joinGroup() {
        MemberService.joinGroup(this.code);
        console.log(this.$route);
      },
      switched(isOn) {
        this.$emit("input", isOn)
      }

}}


</script>

<style scoped>

form.form-new-group{
  min-width: 250px;
}

#new-group {
  z-index: 10;
  position: relative;
}
.join {
  width: 100%;
}
.helper-link {
  text-decoration: underline;
}
.helper-link:hover {
  cursor: pointer;
}
</style>