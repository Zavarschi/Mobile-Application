/**
 * Created by Pavlik on 2016-11-24.
 */


import React, {Component} from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    ListView,
    Button,
    Navigator,
    TouchableOpacity,
    TextInput,
    Image,
    Alert,
} from 'react-native';

import Communications from 'react-native-communications';


export default class EmailScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {email: "", subject: "", body: ""};
        this.goBack = () => {
            this.props.navigator.push({id: 1});
        };

        this.sendEmail = () => {
            let email = this.state.email;
            let subject = this.state.subject;
            let stateBody = this.state.body;
            if (email != "" && subject != "" && stateBody != "") {
                Communications.email([email], null, null, subject, stateBody)
            } else {
                Alert.alert("All fields required");
            }
        };

    }

    render() {
        return (
            <View style={styles.container}>

                <TextInput
                    style={{color:'black', fontSize:50, width:200}}
                    onChangeText={(email) => this.setState({email})}
                    value={this.state.email}
                />
                <TextInput
                    style={{color:'black', fontSize:50, width:200}}
                    onChangeText={(subject) => this.setState({subject})}
                    value={this.state.subject}
                />
                <TextInput
                    style={{color:'black', fontSize:50, width:200}}
                    onChangeText={(body) => this.setState({body})}
                    value={this.state.body}
                />

                <TouchableOpacity onPress={this.sendEmail}>
                    <View
                        style={{alignItems: 'center',width: 150, height: 50,paddingVertical: 10, paddingHorizontal: 20, backgroundColor: '#282828'}}>
                        <Text style={styles.buttontext}>Send email</Text>
                    </View>
                </TouchableOpacity>
                <View
                    style={{alignItems: 'center',width: 150, height: 20,paddingVertical: 10, paddingHorizontal: 20, backgroundColor: 'transparent'}}/>
                <TouchableOpacity onPress={this.goBack}>
                    <View
                        style={{alignItems: 'center',width: 150, height: 50,paddingVertical: 10, paddingHorizontal: 20, backgroundColor: '#282828'}}>
                        <Text style={styles.buttontext}>Back</Text>
                    </View>
                </TouchableOpacity>
            </View>
        );
    }
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        color: 'white',
    },
    buttontext: {
        fontSize: 20,
        textAlign: 'center',
        color: 'white',
    },
    choseonetext: {
        fontSize: 30,
        color: "black"
    },
    row: {
        flex: 1,
        flexDirection: 'row',
        padding: 18,
        borderBottomWidth: 1,
        borderColor: '#d7d7d7',
    },
    selectionText: {
        fontSize: 15,
        paddingTop: 3,
        color: '#282828',
        textAlign: 'right'
    }
});
