<template>
  <div class="modal-content-container fade">
    <form class="form-new-group" @submit.prevent="saveList">
      <h1>Edit List</h1>
      <hr />
      <br />

      <input type="checkbox" class="checkbox" v-model="listClaimed" />
      <label for="checkbox">Claim List</label>

      <label for="text" class="sr-only">
        <span>Name (optional)</span>
        <input
          type="text"
          id="group-name"
          class="form-control"
          v-model="listName"
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
            v-model="listDescription"
          />
        </label>
      </div>

      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Update List
      </button>
      <br />
      <a class="helper-link" v-on:click="switched(false)">
        Cancel
      </a>
    </form>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "new-list",
  props: ["toggleForm"],

  // computed allows us to two-way-bind our state with v-model
  computed: {
    // get the list 'subtree'?...to use 'this.list' 
    // in reactive var getters
    ...mapState(["list"]),

    // this is the listName 'reactive var' we want to use in
    // the html
    // get and set allow it to work with v-model?
    listName: {
      set(listName) {
        this.$store.commit("SET_LIST", { listName });
      },
      get() {
        return this.list.listName;
      },
    },


    // this is the listDescription 'reactive var' we want to use in
    // the html
    listDescription: {
      set(listDescription) {
        this.$store.commit("SET_LIST", { listDescription });
      },
      get() {
        return this.list.listDescription;
      },
    },


    // this is the listClaimed 'reactive var' we want to use in
    // the html
    listClaimed: {
      set(listClaimed) {
        this.$store.commit("SET_LIST", { listClaimed });
        //console.log()
      },
      get() {
        return this.list.listClaimed;
      },
    },
  },
  methods: {
    switched(isOn) {
      this.$emit("input", isOn);
    },
    saveList() {
      this.$store.dispatch("UPDATE_LIST");
      this.switched(false);
    },
    
  },
};
</script>

<style>
  .helper-link {
    text-decoration: underline;
  }
  .helper-link:hover {
    cursor: pointer;
  }
  .modal-content-container {
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .fade {
    animation-duration: .1s !important;
    opacity: 1;
    animation-name: fadein;
  }
  @keyframes fadein {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
