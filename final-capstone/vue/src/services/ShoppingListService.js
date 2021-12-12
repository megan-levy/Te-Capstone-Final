import axios from 'axios';

export default {

    getShoppingList(listId) {
        
        return axios.get(`/lists/${listId}`)
        // .then((response) => {
        //     const shoppingLists = response.date.shoppingLists;
        //     return shoppingLists.find(shoppingList => shoppingList.listId == listId);
        // })
    },
    createShoppingList(groupId, list) {
        return axios.post(`/group/${groupId}/lists`, list, { params: { groupId } });
    },
    list(groupId) {
        return axios.get(`/group/${groupId}/lists`, { params: { groupId } })
    },
    getQTY(listId) {
        return axios.get(`/lists/${listId}/items/count`);
    }
}
