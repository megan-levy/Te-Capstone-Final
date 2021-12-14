<template>
  <div id="shopping-list" class="shopping-list">
    <h1>{{ listName }}</h1>
    <p>{{ listDescription }}</p>
        <div>
      <router-link
        class="addBtn"
        v-on:click="setListId"
        v-bind:to="{ name: 'new-item' }"
        v-if="$store.state.token != ''"
        >+</router-link
      >
    </div>
    <br>
    <br>
    <hr />
    <br>
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
import ShoppingListService from "@/services/ShoppingListService.js";

export default {
  name: "shopping-list",
  data() {
    return {
      listId: "",
      listName: "",
      listDescription: "",
    };
  },
  created() {
    this.setListId();
    // console.log(this.$route.params);
    ShoppingListService.getShoppingList(this.$route.params.listId).then(
      (response) => {
        console.log(response);
        this.listName = response.data.listName;
        this.listDescription = response.data.listDescription;
      }
    );
    this.getItemsList();
  },
  methods: {
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