// import axios from 'axios';

export default {

    list() {
        // return axios.get('/login', user)
        // return 
        let groups = [
            {
                "groupId":"54358380198019238113123123243489199574",
                "name": "Party Supplies",
                "description": "Group for party supplies lists and the people that make the parties happen",
            },
            {
                "groupId": "54358380198019238113123123243489199575",
                "name": "Tao Epsilon Nu",
                "description": "Supplies for the house yo",
            }
        ];
        return {
            "data": groups
        };
    }
}
