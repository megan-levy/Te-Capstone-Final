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

       <div class="toggleCOntainer">
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Join
        </button>
        
      </div>
       <hr />
       <a class="helper-link" v-on:click="switched(false)">Cancel</a>
      </form>
  </div>
</template>
   


<script>


export default {
  name: "join-group",
  props:['toggleForm'],
   data() {
       return{
           item: {
               itemName:"",
               itemAmount: "",
                
           }
       };
   },  
   methods: {
      createNewItem() {},
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
</style>