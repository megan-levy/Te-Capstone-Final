import axios from 'axios';

export default {

    getShoppingList(listId) {
        
        return axios.get(`/lists/${listId}`)
        // .then((response) => {
        //     const shoppingLists = response.date.shoppingLists;
        //     return shoppingLists.find(shoppingList => shoppingList.listId == listId);
        // })
    },
    

}
