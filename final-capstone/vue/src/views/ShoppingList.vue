<template>
  <div id="shopping-list" class="shopping-list">
      <h1>{{listName}}</h1>
      <p>{{listDescription}}</p>
     
       <hr />
      <div class="grocery-list">
        <ul class="vertical-list" id="items-listed">
          <li>  
           <div>
             <h3>
               Lots of wine
             </h3>
            <span>Quantity: </span>
            </div>
          </li>
          <li>  
            <div>
             <h3>Pie</h3>
             <span>Quantity: </span>
             </div>
          </li>
          <li> 
            <div> 
             <h3>pizza</h3>
             <span>Quantity: </span>
             </div>
          </li>
          <li> 
            <div> 
             <h3>carrots</h3>
             <span>Quantity: </span>
             </div>
          </li>
          <li>  
            <div>
             <h3>eggs</h3>
             <span>Quantity: </span>
             </div>
          </li>
        </ul>
      </div>

      <div>
        <!-- <button type="submit">Add Item +</button> -->
         <router-link  button type = "submit" class="addBtn" v-bind:to =" {name: 'new-item'}" v-if="$store.state.token != ''">Add Item</router-link> 
      </div>
  </div>
</template>

<script>
import ShoppingListService from '@/services/ShoppingListService.js'

export default {
  name: "shopping-list",
  data() {
    return {
      listId: 13,
      listName: 'Test List',
      listDescription: 'Please work'
    }
  },
  created() {
    ShoppingListService.getShoppingList(this.$route.params.groupId).then(response => {
      this.listName = response.data.listName;
      this.listDescription = response.data.listDescription;
    });
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