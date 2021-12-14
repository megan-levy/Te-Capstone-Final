import axios from 'axios';

export default {

    getShoppingList(listId) {
        
        return axios.get(`/lists/${listId}`,  { params: { "listId": listId } });
    },
    createShoppingList(groupId, list) {
        return axios.post(`/group/${groupId}/lists`, list, { params: { "groupId": groupId } });
    },
    list(groupId) {
        return axios.get(`/group/${groupId}/lists`, { params: { "groupId": groupId } })
    },
    createNewItem(listId, item) {
        return axios.post(`/lists/${listId}/items`, item, {params: {"listId": listId}});
    },
    getItemList(listId){
        return axios.get(`/lists/${listId}/items`,  { params: { "listId": listId } })
    },
    deleteItemFromList(itemId) {
        return axios.delete(`/items/${itemId}`, {params: {"itemId": itemId}});
    }
    
}
