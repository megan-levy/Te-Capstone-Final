<template>
  <div id="shopping-list" class="shopping-list">
    <div class="named-and-claimed">
      <h1>{{ listName }}</h1>
       <p>This list is claimed by: {{claimedByName}}</p>
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
          v-if="$store.state.token != ''"
          >Add</router-link
        >
        <a
          class="addBtn new-group-button"
          v-on:click="toggleJoin = !toggleJoin"
          v-if="$store.state.token != ''"
          >Edit</a
        >
        <div v-if="toggleJoin" class="modal">
          <edits-toggle
          @howdy="funcRun"
            v-model="toggleJoin"

          />
        </div>
        <a class="addBtn new-group-button" v-if="$store.state.token != ''"
          >Delete</a
        >
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
        <router-link
          tag="a"
          class="group-list-item shopping-list-item"
          v-bind:to="{ name: '', params: { itemId: `${item.itemId}` } }"
          v-for="item in $store.state.items"
          :key="item.itemId"
        >
          <div>
            <h3>
              {{ item.itemName }}
            </h3>
            <span>Quantity: {{ item.itemAmount }}</span>
          </div>
        </router-link>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'; 
import ShoppingListService from "@/services/ShoppingListService.js";
import EditList from "../components/EditList.vue";

export default {
  name: "shopping-list",
  components: {
    editsToggle: EditList,
  },
  data() {
    return {
      // listId: "",
      // listName: "",
      // listDescription: "",
      toggleJoin: false,
      // listClaimed: false
    };
  },
    // computed allows us to two-way-bind our state with v-model
  computed: {
    // get the list 'subtree'?...to use 'this.list'
    // in reactive var getters
    ...mapState(["list", "user"]),

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
          this.$store.commit("SET_LIST", { listClaimedBy: this.user.id});
          // console.log(this.user.username);
        } else {
          this.$store.commit("SET_LIST", {'listClaimedBy': null});
        }
      },
      get() {
        return this.list.listClaimed
       
      },
    },
  },
  created() {
    this.setListId();
    this.$store.dispatch('GET_LIST', this.$route.params.listId);
    this.getItemsList();
  },
  mounted() {
    this.$store.dispatch('GET_LIST', this.$route.params.listId);
  },
  methods: {
    funcRun() {
      setTimeout(() => {
        this.$store.dispatch('GET_LIST', this.$route.params.listId);
      },50)
    },
    setListId() {
      this.$store.commit("SET_LIST_ID", this.$route.params.listId);
    },
    getItemsList() {
      ShoppingListService.getItemList(this.$route.params.listId).then(
        (items) => {
          this.$store.commit("SET_ITEMS", items.data);
        }
      );
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

.named-and-claimed {
  display: flex;
}

#return {
  margin-block-end: 0px;
  padding: 10px;
}

#items-listed {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
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
  max-width: 300px;
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

</style>