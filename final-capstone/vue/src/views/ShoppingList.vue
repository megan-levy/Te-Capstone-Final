<template>
  <div id="shopping-list" class="shopping-list">
    <div class="named-and-claimed">
      <h1>{{ listName }}</h1>
      <p>This list is claimed by: {{ claimedByName }}</p>
    </div>
    <p>{{ listDescription }}</p>

    <!-- IS LIST CLAIMED? {{list.listName}} -->

    <div>
      <hr />
      <div class="buttons-groups">
        <router-link
          class="addBtn new-group-button"
          v-on:click="setListId"
          v-bind:to="{ name: 'new-item' }"
          v-if="$store.state.token != '' && editable"
          >Add</router-link
        >
        <a
          class="addBtn new-group-button"
          v-on:click="toggleJoin = !toggleJoin"
          v-if="$store.state.token != '' && editable"
          >Edit</a
        >
        <a
          class="addBtn new-group-button"
          @click="clearList"
          v-if="$store.state.token != '' && editable"
          >Clear</a
        >
        <div v-if="toggleJoin" class="modal">
          <edits-toggle
            @howdy="funcRun"
            v-if="$store.state.token != '' && editable"
            v-model="toggleJoin"
          />
        </div>
        <div v-if="toggleItemEdit" class="modal">
          <edit-item @updateAndClose="funcRun" v-model="toggleItemEdit" />
        </div>
        <div v-if="editable">
          <a class="addBtn new-group-button" v-if="$store.state.token != ''"
            >Delete</a
          >
        </div>
      </div>
    </div>

    <hr />
    <br />
    <router-link
      id="return"
      tag="a"
      class="helper-link"
      :to="{ name: 'group-home' }"
    >
      Return to My Lists
    </router-link>
    <div class="grocery-list">
      <ul class="vertical-list" id="items-listed">
        <a
          class="group-list-item shopping-list-item"
          v-for="item in this.items"
          :key="item.listItemId"
        >
          <div class="item-card">
            <div class="top-line">
              <h3>
                {{ item.itemName }}
              </h3>
              <span>Quantity: {{ item.itemAmount }}</span>
            </div>
           
            <div v-if="editable" class="list-item-editor">
              <a
                class="edit-btn"
                @click="
                  () => {
                    startItemEdit(item);
                  }
                "
                >Edit</a
              >

              <a
                class="del-btn"
                @click="
                  (e) => {
                    deleteItem(e, item.listItemId);
                  }
                "
                >Delete</a
              >
            </div>
          </div>
        </a>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import EditList from "../components/EditList.vue";
import EditItem from "../components/EditItem.vue";

export default {
  name: "shopping-list",
  components: {
    editsToggle: EditList,
    EditItem,
  },
  data() {
    return {
      toggleJoin: false,
      toggleItemEdit: false,
    };
  },
  // computed allows us to two-way-bind our state with v-model
  computed: {
    // get the list 'subtree'?...to use 'this.list'
    // in reactive var getters
    ...mapState(["list", "user", "items"]),

    // this is the listName 'reactive var' we want to use in
    // the html
    // get and set allow it to work with v-model?
    itemsStore: {
      get() {
        return this.items;
      },
    },
    listName: {
      set(listName) {
        this.$store.commit("SET_LIST", { listName });
      },
      get() {
        return this.list.listName;
      },
    },
    editable: function () {
      return (
        !this.list.listClaimed ||
        (this.list.listClaimed &&
          parseInt(this.user.id) === parseInt(this.list.listClaimedBy))
      );
    },
    claimedByName: {
      set(claimedByName) {
        this.$store.commit("SET_LIST", { claimedByName });
        // this.list.listClaimed && this.list.listClaimedBy ? this.$store.commit("SET_LIST", { claimedByName }) : this.$store.commit("SET_LIST", { claimedByName: null });
      },
      get() {
        return this.list.claimedByName;
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
        if (listClaimed) {
          this.$store.commit("SET_LIST", { listClaimedBy: this.user.id });
        } else {
          this.$store.commit("SET_LIST", { listClaimedBy: null });
        }
      },
      get() {
        return this.list.listClaimed;
      },
    },
  },
  created() {
    this.setListId();
    this.$store.dispatch("GET_LIST", this.$route.params.listId);

    this.getItemsList();
  },
  mounted() {
    this.$store.dispatch("GET_LIST", this.$route.params.listId);
  },
  methods: {
    funcRun() {
      this.toggleItemEdit = false;
      setTimeout(() => {
        this.$store.dispatch("GET_LIST", this.$route.params.listId).then(() => {
          this.$store.dispatch("GET_ITEMS", this.$route.params.listId);
        });
      }, 100);
    },
    startItemEdit(item) {
      if (item && item.listItemId) {
        // , item.listItemId
        this.$store.dispatch("GET_ITEM", item.listItemId).then(() => {
          this.toggleItemEdit = !this.toggleItemEdit;
        });
      }
    },
    setListId() {
      this.$store.commit("SET_LIST_ID", this.$route.params.listId);
    },

    getItemsList() {
      this.$store.dispatch("GET_ITEMS", this.$route.params.listId);
    },
    deleteItem(e, itemId) {
      if (!this.editable) return;
      this.$store.dispatch("DELETE_ITEM", itemId);
    },
    clearList() {
      if (!this.editable) return;
      this.$store.dispatch("DELETE_LIST_ITEMS", this.$route.params.listId);
    },
  },
};
</script>

<style>
.shopping-list {
  height: fit-content;
  min-width: 350px;
  margin: 15px;
  width: calc(100% - 80px);
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  border-radius: 6px;
  filter: drop-shadow(0px 4px 4px rgba(221, 221, 221, 0.25));
  padding: 25px;
  height: calc(100% - 120px);
}

.list-item-editor {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.named-and-claimed {
  display: flex;
  flex-wrap: wrap;
}

#return {
  margin-block-end: 0px;
  padding: 10px;
}

#items-listed {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  width: 100%;
  padding-inline-start: 0px;
}

h1 {
  padding: 10px 10px 10px 0;
}

p {
  margin: 20px;
  font-style: italic;
}

h3 {
  font-size: 18px;
  margin-block-start: 0;
  color: #1f7a8c;
}

li span {
  font-size: 14px;
  padding-top: 20px;
}
li {
  background-color: #e1e5f2;
  padding: 15px;
  margin: 10px;
  border-radius: 6px;
  list-style-type: none;
  display: flex;
  border: 2px solid transparent;
  transition: all 250ms cubic-bezier(0.215, 0.61, 0.355, 1);
  /* box-shadow: 0px 0px 5px 5px rgb(237 237 237 / 25%); */
}
li:hover {
  border: 2px solid #1f7a8c;
  box-shadow: 0px 0px 5px 5px rgb(237 237 237 / 35%);
  cursor: pointer;
}

#x {
  width: 23px;
  height: 23px;
  border: 2px solid #eef5df;

  border-radius: 50%;
}
.grocery-list {
  overflow-y: scroll;
  height: 100%;
}
.shopping-list-item {
  max-width: 175px;
}

@media only screen and (max-width: 600px) {
  .shopping-list-item {
    max-width: 100%;
  }
  #items-listed {
    display: flex;
    width: 100%;
    flex-direction: column;
    -webkit-padding-start: 0px;
    padding-inline-start: 0px;
  }
  .shopping-list {
    height: -webkit-fit-content;
    height: -moz-fit-content;
    height: calc(100% - 95px);
    width: calc(100% - 60px);
    margin: 20px;
    min-width: 250px;
    background-color: #ffffff;
    border-radius: 6px;
    filter: drop-shadow(0px 4px 4px rgba(221, 221, 221, 0.25));
    padding: 10px;
  }
}
.del-btn {
  background-color: #1f7a8c;
  border-radius: 6px;
  color: #ffffff;
  border: 2px solid #1f7a8c;
  padding: 10px;
  text-decoration: none;
}
.del-btn:hover {
  background-color: transparent;
  color: #1f7a8c;
}
.top-line {
  display: flex;
  justify-content: space-between;
}
.item-card {
  display: flex;
  flex-direction: column;
  min-height: 150px;
  justify-content: space-between;
}

.edit-btn {
  background-color: #1f7a8c;
  border-radius: 6px;
  color: #ffffff;
  border: 2px solid #1f7a8c;
  padding: 10px;
  text-decoration: none;
}
.edit-btn:hover {
  background-color: transparent;
  color: #1f7a8c;
}
</style>