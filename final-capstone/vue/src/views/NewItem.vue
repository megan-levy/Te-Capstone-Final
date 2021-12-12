<template>
  <div id="new-group" class="text-center auth-container">
      <form class="form-new-group" @submit.prevent="createItem">
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
            <span>Add Quantity Needed:</span>
            <textarea placeholder="Enter A Quantity" class="form-control" v-model="item.itemAmount"/>
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Item
        </button>
       <router-link tag="a" class="helper-link" :to="{ name: 'groups' }">Cancel</router-link>
      </form>
  </div>
</template>
   


<script>
export default {
  name: "new-item",
   data() {
       return{
           item: {
               itemName:"",
               itemAmount: "",
           },
       };
   },  

}


</script>

<style>

</style>