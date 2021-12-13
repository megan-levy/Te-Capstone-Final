<template>
  <div id="new-group" class="text-center auth-container">
     
      <form class="form-new-group" @submit.prevent="createNewItem">
        <h1 class="h3 mb-3 font-weight-normal">Create New Item</h1>
        <hr>
        <br>
        <label for="text" class="sr-only">
            <span>Item Name:</span>
            <input
            type="text"
            id="group-name"
            class="form-control"
            v-model="item.itemName"
            placeholder="Item Name..."
            required/>
        </label>
        <label for='text' class="sr-only">
            <!-- <span>Add Quantity Needed:</span>
            <textarea placeholder="Enter A Quantity" class="form-control" v-model="item.itemAmount"/> -->
            <label for="item-counts">Enter Item Quantity (1-10): </label>
            <input type="number" class="form-control" name="item-counts" v-model="item.itemAmount" min="1" max="10" >

        </label>

        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Item
        </button>
       <router-link tag="a" class="helper-link" :to="{ name: 'shopping-list' }">Cancel</router-link>
      </form>
  </div>
</template>
   


<script>

import shoppingListService from "../services/ShoppingListService";
export default {
  name: "new-item",
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
<<<<<<< HEAD
            this.$router.push("/");
            console.log("successful")
=======
            let newRoute = this.$route.fullPath.split('/');
            newRoute.splice(newRoute.length - 1);
            this.$router.push(newRoute.join('/'));
>>>>>>> 3eef6d15e39ac2ce9a5c59115929d4731b0b6d5e
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