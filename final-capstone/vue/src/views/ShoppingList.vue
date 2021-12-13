<template>
  <div id="shopping-list" class="shopping-list">
     
      <h1>{{listName}}</h1>
     
      <p>{{listDescription}}</p>

       <router-link
        id = "return"
        tag="a"
        class="helper-link"
        :to="{ name: 'group-home' }"
      >
        Return to My Groups
      </router-link>
     
       <hr />
      <div class="grocery-list">
        <ul class="vertical-list" id="items-listed">
          <router-link 
          tag="a" 
          class="group-list-item" 
          v-bind:to="{ name: '', params: { itemId: `${item.itemId}` }}"  
          v-for="item in $store.state.items" 
          :key="item.itemId"
        >
          <div>
            <h3>
              {{item.itemName}}
              </h3>
            <span>Quantity: {{item.itemAmount}}</span>
          </div>
        
        </router-link>
         
        </ul>
      </div>

      <div>
        <router-link class="addBtn" v-on:click="setListId"  v-bind:to="{ name: 'new-item' }" v-if="$store.state.token != ''">+</router-link>
       
      </div>
  </div>
</template>

<script>
import ShoppingListService from '@/services/ShoppingListService.js'

export default {
  name: "shopping-list",
  data() {
    return {
      listId: '',
      listName: '',
      listDescription: ''
    }
  },
  created() {
    this.setListId();
    ShoppingListService.getShoppingList(this.$route.params.groupId).then(response => {
      this.listName = response.data.listName;
      this.listDescription = response.data.listDescription;
    });
    this.getItemsList();
  },
  methods: {
    setListId(){
       this.$store.commit("SET_LIST_ID", this.$route.params.listId);
    },
    getItemsList(){
      ShoppingListService.getItemList(this.$route.params.listId).then(items => {
        this.$store.commit("SET_ITEMS", items.data);
      })
    }
  }
}
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
}

#return {
   margin-block-end: 0px;
   padding: 10px;
}

#items-listed {
  display: grid;
  grid-template-columns:repeat(auto-fit, minmax(250px, 1fr));
  width: 100%;
  padding-inline-start: 0px;
}

h1 {
  padding: 10px;
  
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
    transition: all 250ms cubic-bezier(0.215, 0.610, 0.355, 1);
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
</style>