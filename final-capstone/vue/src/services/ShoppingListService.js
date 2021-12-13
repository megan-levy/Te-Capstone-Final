import axios from 'axios';

export default {

    getShoppingList(listId) {
        
        return axios.get(`/lists/${listId}`,  { params: { "listId": listId } })
        // .then((response) => {
        //     const shoppingLists = response.date.shoppingLists;
        //     return shoppingLists.find(shoppingList => shoppingList.listId == listId);
        // })
    },
    createShoppingList(groupId, list) {
        return axios.post(`/group/${groupId}/lists`, list, { params: { "groupId": groupId } });
    },
    list(groupId) {
        return axios.get(`/group/${groupId}/lists`, { params: { "groupId": groupId } })
    },
    // getTotalItemsInList(listId) {
    //     return axios.get(`/lists/${listId}/items/count`, {params: {"listId": listId}});
    // },
    createNewItem(listId, item) {
        console.log(listId);
        console.log("-----------");
        console.log(item);
        return axios.post(`/lists/${listId}/items`, item, {params: {"listId": listId}});
    },
    getItemList(listId){
        return axios.get(`/lists/${listId}/items`,  { params: { "listId": listId } })
    }
}
