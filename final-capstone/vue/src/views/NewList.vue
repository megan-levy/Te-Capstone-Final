<template>
  <div id="new-group" class="text-center auth-container">
    <form class="form-new-group" @submit.prevent="createList">
      <h1>New List</h1>
      <hr />
      <br />
      <label for="text" class="sr-only">
        <span>Name</span>
        <input
          type="text"
          id="group-name"
          class="form-control"
          v-model="list.listName"
          placeholder="List Name..."
          required
        />
      </label>

      <label for="text" class="sr-only">
        <span>Description</span>
        <textarea
          placeholder="Enter A Description..."
          class="form-control"
          v-model="list.listDescription"
        />
      </label>

      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Add List
      </button>
      <br />
      <router-link tag="a" class="helper-link" :to="{ name: 'groups' }"
        >Cancel</router-link
      >
    </form>
  </div>
</template>

<script>
import shoppingListService from "../services/ShoppingListService";
export default {
  name: "new-list",
  data() {
    return {
      list: {
        listName: "",
        listDescription: "",
      },
    };
  },
  methods: {
    createList() {
      shoppingListService
        .createShoppingList(this.$store.state.group.groupId, this.list)
        .then((response) => {
          if (response.status == 200 || response.status == 201) {
            let newRoute = this.$route.fullPath.split("/");
            newRoute.splice(newRoute.length - 1);
            this.$router.push(newRoute.join("/"));
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
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