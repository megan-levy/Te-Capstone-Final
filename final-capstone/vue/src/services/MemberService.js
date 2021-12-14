import axios from 'axios';

export default {

    //for joining group with valid id
    joinGroup(code) {
        console.log(code);

        let data = {
            code
        }
        return axios.post(`/members`, data);
    },
    //for joining group with valid id
    leaveGroup(groupId) {
        return axios.delete(`/members/${groupId}`, { params: { "groupId": groupId }});
    },
}