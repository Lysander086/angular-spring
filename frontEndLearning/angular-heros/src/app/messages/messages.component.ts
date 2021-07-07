import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';


const CALCULATOR_SERVICE_FIX = 'com.richdataco.cde.decision.function.';

const CALCULATOR_SERVICE = {
  /*calculatorService: {
      calculateEligibleLoanOffer: {
          name: `${CALCULATOR_SERVICE_FIX}CalculatorService#calculateEligibleLoanOffer`,
          returnType: 'object',
          type: 'function',
          parameters: [
              {
                  type: 'variable',
                  key: '',
                  dataType: '',
              }, {
                  type: 'variable',
                  key: '',
                  dataType: '',
              }, {
                  type: 'variable',
                  key: '',
                  dataType: '',
              }, {
                  type: 'variable',
                  key: '',
                  dataType: '',
              }, {
                  type: 'function',
                  name: 'com.richdataco.cde.decision.function.RuleDecisionTableService#getParameter',
                  returnType: 'object',
                  parameters: [
                      {type: 'input', value: 'PT007', dataType: 'string'}
                  ]
              }, {
                  type: 'function',
                  name: 'com.richdataco.cde.decision.function.RuleDecisionTableService#getParameter',
                  returnType: 'object',
                  parameters: [
                      {type: 'input', value: 'PT007', dataType: 'string'}
                  ]
              }, {
                  type: 'function',
                  name: 'com.richdataco.cde.decision.function.RuleDecisionTableService#getParameter',
                  returnType: 'object',
                  parameters: [
                      {type: 'input', value: 'PT007', dataType: 'string'}
                  ]
              }, {
                  type: 'math',
                  returnType: 'integer',
                  expression: '',
                  parameters: []
              }, {
                  type: 'function',
                  name: 'com.richdataco.cde.decision.function.RuleDecisionTableService#getParameter',
                  returnType: 'object',
                  parameters: [
                      {type: 'input', value: 'PT007', dataType: 'string'}
                  ]
              }, {
                  type: 'math',
                  returnType: 'integer',
                  expression: '',
                  parameters: []
              }, {
                  name: 'output',
                  type: 'bean',
                  properties: [
                      {type: 'property', name: '', value: {type: 'input', value: 'maxAmount', dataType: 'string'}},
                      {type: 'property', name: '', value: {type: 'input', value: 'instalmentAmount', dataType: 'string'}},
                      {type: 'property', name: '', value: {type: 'input', value: 'loanTermRepayments', dataType: 'string'}}
                  ]
              }
          ]
      },
      getInstalmentAmountByRepayments: {
          name: `${CALCULATOR_SERVICE_FIX}CalculatorService#getInstalmentAmountByRepayments`,
          returnType: 'big-decimal',
          type: 'function',
          parameters: [
              {
                  type: 'variable',
                  key: '',
                  dataType: '',
              }, {
                  type: 'function',
                  name: 'com.richdataco.cde.decision.function.RuleDecisionTableService#getParameter',
                  returnType: 'object',
                  parameters: [
                      {type: 'input', value: 'PT009', dataType: 'string'}
                  ]
              }, {
                  type: 'variable',
                  key: '',
                  dataType: '',
              }, {
                  type: 'variable',
                  key: '',
                  dataType: '',
              }
          ]
      }

  },*/
  merchantLendingCalculator: {
    calculate: {
      name: `${CALCULATOR_SERVICE_FIX}MerchantLendingCalculator#calculate`,
      returnType: 'object',
      type: 'function',
      parameters: [
        {
          type: 'variable',
          key: '',
          dataType: ''
        }, {
          type: 'variable',
          key: '',
          dataType: ''
        }, {
          type: 'variable',
          key: '',
          dataType: ''
        }, {
          type: 'variable',
          key: '',
          dataType: ''
        }, {
          name: 'output',
          type: 'bean',
          properties: [
            { type: 'property', name: '', value: { type: 'input', value: 'loanAmount', dataType: 'string' } },
            { type: 'property', name: '', value: { type: 'input', value: 'feesAmount', dataType: 'string' } },
            {
              type: 'property',
              name: '',
              value: { type: 'input', value: 'estimatedRepaymentTerm', dataType: 'string' }
            },
            {
              type: 'property',
              name: '',
              value: { type: 'input', value: 'estimatedRepaymentAmount', dataType: 'string' }
            },
            { type: 'property', name: '', value: { type: 'input', value: 'totalRepaymentAmount', dataType: 'string' } }
          ]
        }
      ]
    }
  },
  methodBean: {
    getAge: {
      name: `${CALCULATOR_SERVICE_FIX}MethodBean#getAge`,
      returnType: 'integer',
      type: 'function',
      parameters: [
        {
          type: 'variable',
          key: '',
          dataType: ''
        }
      ]
    },
    getNumberOfMonths: {
      name: `${CALCULATOR_SERVICE_FIX}MethodBean#getNumberOfMonths`,
      returnType: 'integer',
      type: 'function',
      parameters: [
        {
          type: 'variable',
          key: '',
          dataType: ''
        },
        {
          type: 'variable',
          key: '',
          dataType: ''
        }
      ]
    },
    getHourOfDay: {
      name: `${CALCULATOR_SERVICE_FIX}MethodBean#getHourOfDay`,
      returnType: 'integer',
      type: 'function',
      parameters: [
        {
          type: 'variable',
          key: '',
          dataType: ''
        }
      ]
    }
  }
};


@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  constructor(public messageService: MessageService) {
  }

  // markz: play ts here
  ngOnInit() {
    console.log(Object.keys(CALCULATOR_SERVICE), Object.keys(CALCULATOR_SERVICE).length);
    Object.keys(CALCULATOR_SERVICE).forEach(key => {
      if (CALCULATOR_SERVICE.hasOwnProperty(key)) {
        console.log(true);
      }

    });


  }

}
