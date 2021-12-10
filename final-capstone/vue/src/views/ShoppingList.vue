<template>
  <div id="shopping-list" class="shopping-list">
      <h1>{{listName}}</h1>
      <p>{{listDescription}}</p>
      <div>
        <ul>
          <li>  
             <h3>
               Lots of wine
             </h3>
          </li>
          <li>  
             <h3>Pie</h3>
          </li>
        </ul>
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
    ShoppingListService.getShoppingList(this.$route.params.listId).then(response => {
      this.listName = response.data.listName;
      this.listDescription = response.data.listDescription;
    })
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
</style>