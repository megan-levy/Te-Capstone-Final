<template>
  <div>
       <form class="form-new-group" @submit.prevent="updateListContent">
        <h1>Edit List</h1>
      <hr />
      <br />
      <label for="text" class="sr-only">
        <span>Name (optional)</span>
        <input
          type="text"
          id="group-name"
          class="form-control"
          v-model="listContent.listName"
          placeholder="List Name..."
          required
        />
      </label>

    <div class="toggleContainer">
      <label for="text" class="sr-only">
        <span>Description (optional)</span>
        <textarea
          placeholder="Enter A Description..."
          class="form-control"
          v-model="listContent.listDescription"
        />
      </label>
    </div>

      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Update List
      </button>
      <br />
      <router-link tag="a" class="helper-link" 
      :to="{ name: 'shopping-list' }" 
      v-on:click="switched(false)"
        >Cancel</router-link
      >
        </form>
  </div>
</template>

<script>
//import shoppingListService from "../services/ShoppingListService";
export default {
  name: "new-list",
  props: ['list', 'toggleForm'],
  data() {
    return {
      listContent: this.$props.list 
    };
  },
  methods: {
      updateListContent() {
        //deconstructing the listContent
        //   let {
        //       id,
        //       name,
        //       description  
        //   } = {...JSON.parse(JSON.stringify(this.listContent))}

        console.log(this.listContent.listId)
        console.log(this.listContent.listName)
        console.log(this.listContent.listDescription)
      },
      switched(isOn) {
        this.$emit("input",isOn);
      }
  }

}
</script>

<style>

</style>