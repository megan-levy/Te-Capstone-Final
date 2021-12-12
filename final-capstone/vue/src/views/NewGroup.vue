<template>
  <div id="new-group" class="text-center auth-container">
      <form class="form-new-group" @submit.prevent="createGroup">
        <h1 class="h3 mb-3 font-weight-normal">Create New Group</h1>
         <hr>
         <br>
         <label for="text" class="sr-only">
             <span>Group Name:</span>
             <input
             type="text"
             id="group-name"
             class="form-control"
             v-model="group.groupname"
             placeholder="Group Name..."
             required/>
         </label>
         <label for='text' class="sr-only">
             <span>Add Group Description:</span>
             <textarea placeholder="Enter A Description..." class="form-control" v-model="group.groupDescription"/>
         </label>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Group
      </button>
       <router-link tag="a" class="helper-link" :to="{ name: 'groups' }">Cancel</router-link>
      </form>
  </div>
</template>

<script>
import groupService from "../services/GroupService";
export default {
  name: "newgroup",
   data() {
    return {
      group: {
        groupname: "",
        groupDescription: "",
      },
    };
  },
  methods: {
    createGroup() {
     groupService.createGroup(this.group)
      .then((response) => {
          if (response.status == 200 || response.status == 201) {
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }

}
</script>

<style>
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
</style>