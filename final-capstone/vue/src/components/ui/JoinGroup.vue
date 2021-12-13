<template>
  <div id="new-group" class="text-center auth-container">
     
      <form class="form-new-group" @submit.prevent="createNewItem">
        <h1 class="h3 mb-3 font-weight-normal">Join Group</h1>
        <hr>
        <br>
        <label for="text" class="sr-only">
            <span>Enter Access Code:</span>
            <input
            type="text"
            id="group-name"
            class="form-control"
            v-model="item.itemName"
            placeholder="Enter 6 Digit Access Code..."
            required/>
        </label>

        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Join
        </button>
       <router-link tag="a" class="helper-link" :to="{ name: 'shopping-list' }">Cancel</router-link>
      </form>
  </div>
</template>
   


<script>

import shoppingListService from "../services/ShoppingListService";
export default {
  name: "join-group",
   data() {
       return{
           item: {
               itemName:"",
               itemAmount: "",
           }
       };
   },  
   methods: {
      createNewItem() {
        if (this.$store.state.listId !== 0){
     shoppingListService.createNewItem(this.$store.state.listId, this.item)
      .then((response) => {
          if (response.status == 200 || response.status == 201) {
            let newRoute = this.$route.fullPath.split('/');
            newRoute.splice(newRoute.length - 1);
            this.$router.push(newRoute.join('/'));
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });}
    }
  }

}


</script>

<style>

</style>