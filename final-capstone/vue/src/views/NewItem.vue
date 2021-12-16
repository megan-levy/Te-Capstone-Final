<template>
  <div id="new-group" class="text-center auth-container">
    <form class="form-new-group" @submit.prevent="createNewItem">
      <h1 class="h3 mb-3 font-weight-normal">Create New Item</h1>
      <hr />
      <br />
      <div class="form-items">
        <label for="text" class="sr-only name-label">
          <span>Name</span>
          <input
            type="text"
            id="group-name"
            class="form-control"
            v-model="item.itemName"
            placeholder="Item Name..."
            required
          />
        </label>
        <label for="text" class="sr-only amount-label">
          <!-- <span>Add Quantity Needed:</span>
            <textarea placeholder="Enter A Quantity" class="form-control" v-model="item.itemAmount"/> -->
          <span
            aria-label="Decrement"
            class="spinner left"
            @click="item.itemAmount > 1 ? item.itemAmount-- : (itemAmount = 1)"
            >-</span
          >
          <label for="item-counts" class="sr-only">
            <span>Qty</span>
            <div class="amount-container">
              <input
                type="number"
                class="form-control amount"
                name="item-counts"
                v-model="item.itemAmount"
                value="1"
                min="1"
                max="10"
              />
            </div>
          </label>
          <span
            aria-label="Increment"
            class="spinner right"
            @click="
              item.itemAmount < 10 ? item.itemAmount++ : (itemAmount = 10)
            "
          >
            +
          </span>
        </label>
      </div>
      <br />
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Item
      </button>
      <br />
      <router-link tag="a" class="helper-link" :to="{ name: 'shopping-list' }"
        >Cancel</router-link
      >
    </form>
  </div>
</template>
   
<script>
import shoppingListService from "../services/ShoppingListService";
export default {
  name: "new-item",
  data() {
    return {
      item: {
        itemName: "",
        itemAmount: "",
      },
    };
  },
  methods: {
    createNewItem() {
      if (this.$store.state.listId !== 0) {
        shoppingListService
          .createNewItem(this.$store.state.listId, this.item)
          .then((response) => {
            if (response.status == 200 || response.status == 201) {
              let newRoute = this.$route.fullPath.split("/");
              newRoute.splice(newRoute.length - 1);
              this.$store.dispatch('GET_LISTS', this.$route.params.groupId);
              setTimeout(() => {
                this.$router.push(newRoute.join("/"));
              }, 50);
            }
          })
          .catch((error) => {
            const response = error.response;

            if (response.status === 401) {
              this.invalidCredentials = true;
            }
          });
      }
    },
  },
};
</script>

<style scoped>
.spinner {
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  font-size: 1.5rem;
  line-height: 1.5rem;
  height: 40px;
  position: relative;
  bottom: 0;
  display: flex;
  align-items: center;
  align-self: end;
  text-align: center;
  padding: 0 10px;
  margin-bottom: 0;
}
.spinner:hover {
  cursor: pointer;
}
.spinner.left:hover {
  color: #ff6161;
}
.spinner.right:hover {
  color: #2a9eb5;
}
.amount {
  padding: 10px;
  background-color: #f3f6fa;
  border: none;
  border-radius: 4px;
  width: 1rem;
  text-align: center;
}
.amount::-webkit-outer-spin-button,
.amount::-webkit-inner-spin-button {
  -webkit-appearance: none;
}
.amount-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 40px;
}
.form-items {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(50px, 1fr));
  justify-content: space-between;
  grid-gap: 12px;
}
.amount-label {
  flex-direction: row;
  grid-column-end: 10;
  /* grid-column-start: 6; */
}
.name-label {
  grid-column-end: 3;
}
.sr-only {
  margin-bottom: 0 !important;
}
</style>