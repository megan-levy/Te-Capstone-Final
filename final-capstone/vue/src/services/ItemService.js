import axios from 'axios';

export default {

    createNewItem(listId, item) {
        return axios.post(`/lists/${listId}/items`, item, {params: {"listId": listId}});
    },
    getItemList(listId) {
        return axios.get(`/lists/${listId}/items`,  { params: { "listId": listId } });
    },
    getSingleItem(itemId) {
        return axios.get(`/items/${itemId}`, { params: { "itemId": itemId } });
    },
    updateItem(item, itemId) {
        return axios.put(`/items/${itemId}`, item, { params: { "itemId": itemId } });
    },
    deleteItemFromList(itemId) {
        return axios.delete(`/items/${itemId}`, {params: {"itemId": itemId}});
    }
}