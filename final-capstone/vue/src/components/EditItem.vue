<template>
  <div class="modal-content-container fade">
    <form class="form-new-group" @submit.prevent="updateItem">
      <h1>Edit Item</h1>
      <hr />
      <br />
      <div class="form-items">
        <label for="text" class="sr-only name-label">
          <span>Name (optional)</span>
          <input
            type="text"
            id="group-name"
            class="form-control"
            v-model="itemName"
            placeholder="Item Name..."
            required
          />
        </label>
        <label for="text" class="sr-only amount-label" id = "counter-wheel">
          <!-- <span>Add Quantity Needed:</span>
            <textarea placeholder="Enter A Quantity" class="form-control" v-model="item.itemAmount"/> -->
          <span
            aria-label="Decrement"
            class="spinner left"
            @click="itemAmount > 1 ? itemAmount-- : (itemAmount = 1)"
            >-</span
          >
          <label for="item-counts" class="sr-only">
            <span>Qty</span>
            <div class="amount-container">
              <input
                type="number"
                class="form-control amount"
                name="item-counts"
                v-model="itemAmount"
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
              itemAmount < 10 ? itemAmount++ : (itemAmount = 10)
            "
          >
            +
          </span>
        </label>
      </div>
      <br />
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Update Item
      </button>
      <br />
      <a class="helper-link"
      @click="() => {switched(false)}"
        >Cancel</a
      >
    </form>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "new-list",
  props: ["toggleForm"],


  computed: {
    ...mapState(["list", "user", "item"]),
    itemName: {
      set(itemName) {
        this.$store.commit("SET_ITEM", { itemName });
      },
      get() {
        return this.item.itemName;
      },
    },
    itemAmount: {
      set(itemAmount) {
        this.$store.commit("SET_ITEM", { itemAmount });
      },
      get() {
        return this.item.itemAmount;
       
      },
    },
  },
  methods: {
    switched(isOn) {
      this.$emit("updateAndClose", isOn);
    },
    updateItem() {
     if ((this.item.itemName.length < 1)) {
        alert("List items must have a name. Either delete the item or add a name");
        return;
     }

      this.$store.dispatch("UPDATE_ITEM").then(() => {
          this.$store.dispatch(
            "GET_ITEMS", 
            this.$route.params.listId)
            .then(() =>  
            setTimeout(
              () => this.switched(false),
              50
            ));
      });
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
  animation-duration: 0.1s !important;
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

#counter-wheel {
  flex-direction: row;
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
</style>
