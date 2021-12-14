import axios from 'axios';

export default {

    list() { 
        return axios.get('/groups')
    },
    createGroup(group) { 
        return axios.post('/groups', group);
    },
    getSingle(groupId) {
        return axios.get(`/groups/${groupId}`, { params: { "groupId": groupId } });
    },
    listByUserId() {
        return axios.get(`/groups/user`);
    },
    updateGroup(group, groupId) {
        return axios.put(`/groups/${groupId}`, group, { params: { "groupId": groupId } });
    },
    deleteGroup(groupId) {
        return axios.delete(`/groups/${groupId}`, { params: { "groupId": groupId } });
    },
    // //for joining group with valid id
    // joinGroup(code, groupId) {
    //     return axios.post(`/members/${groupId}`, code, { params: { "groupId": groupId }});
    // },
    // //for joining group with valid id
    // leaveGroup(groupId) {
    //     return axios.delete(`/members/${groupId}`, { params: { "groupId": groupId }});
    // },


}
